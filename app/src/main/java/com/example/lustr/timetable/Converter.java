package com.example.lustr.timetable;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class Converter  extends AppCompatActivity {

    private String inputFile;
    Button buttonOpenDialog;
    Button buttonUp;
    TextView textFolder;
    static final int CUSTOM_DIALOG_ID = 0;
    ListView dialog_ListView;

    File root;
    File curFolder;

    private List<String> fileList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        buttonOpenDialog = (Button) findViewById(R.id.btnopn);
        buttonOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(CUSTOM_DIALOG_ID);
            }
        });
        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        curFolder = root;
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        Dialog dialog = null;

        switch (id) {
            case CUSTOM_DIALOG_ID:
                dialog = new Dialog(Converter.this);
                dialog.setContentView(R.layout.dialoglayout);
                dialog.setTitle("Dialog");
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                textFolder = (TextView) dialog.findViewById(R.id.folder);
                buttonUp = (Button) dialog.findViewById(R.id.up);
                buttonUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ListDir(curFolder.getParentFile());
                    }
                });

                dialog_ListView = (ListView) dialog.findViewById(R.id.dialoglist);
                dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        File selected = new File(fileList.get(position));
                        if(selected.isDirectory()) {
                            ListDir(selected);
                        } else {
                            try {
                                setInputFile(selected.toString());
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(Converter.this, selected.toString() + " selected",
                                    Toast.LENGTH_LONG).show();
                            dismissDialog(CUSTOM_DIALOG_ID);
                        }
                    }
                });

                break;
        }
        return dialog;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case CUSTOM_DIALOG_ID:
                ListDir(curFolder);
                break;
        }
    }

    void ListDir(File f) {
        if(f.equals(root)) {
            buttonUp.setEnabled(false);
        } else {
            buttonUp.setEnabled(true);
        }

        curFolder = f;
        textFolder.setText(f.getPath());

        File[] files = f.listFiles();
        fileList.clear();

        for(File file : files) {
            fileList.add(file.getPath());
        }

        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, fileList);
        dialog_ListView.setAdapter(directoryList);
    }




    public void setInputFile(String inputFile) throws FileNotFoundException {
        this.inputFile = inputFile;
        this.cread();
    }

    public void cread() throws FileNotFoundException {
        File f = new File(inputFile);
        if (f.exists()) {

            try {
                FileInputStream fi = new FileInputStream(f);
                XSSFWorkbook w = new XSSFWorkbook(fi);
                XSSFSheet sheet = w.getSheetAt(0);
                Log.i("parent", "sheet");
                int rc = sheet.getPhysicalNumberOfRows();
                Log.i("parent", String.valueOf(rc));
                int cc;
                String value;
                FormulaEvaluator formulaEvaluator = w.getCreationHelper().createFormulaEvaluator();
                StringBuilder sb = new StringBuilder();
                for (int r = 1; r < rc; r++) {
                    Row row = sheet.getRow(r);
                    cc = row.getPhysicalNumberOfCells();
                    for (int c = 0; c < cc; c++) {
                        if (c >6 ) {
                            Log.e("parent", " File Format is incorrect! ");
                            break;
                        } else {
                            value = getCellAsString(row, c, formulaEvaluator);
                            Log.i("parent", value);
                            sb.append(value + ",");
                        }
                    }
                    sb.append(":");
                }
                String sbi = sb.toString();
                Log.i("parent", "readExcelData: STRINGBUILDER: " + sbi);


            } catch (IOException e) {
                Log.e("parent", "error" + e.getMessage());
            }

        } else {
            Log.i("parent", "file doesnot exist");
        }
    }

    private String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
        String value = "";
        try {
            Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = "" + cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    double numericValue = cellValue.getNumberValue();
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        double date = cellValue.getNumberValue();
                        SimpleDateFormat formatter =
                                new SimpleDateFormat("MM/dd/yy");
                        value = formatter.format(HSSFDateUtil.getJavaDate(date));
                    } else {
                        value = "" + numericValue;
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = "" + cellValue.getStringValue();
                    break;
                default:
            }
        } catch (NullPointerException e) {

            Log.e("parent", "getCellAsString: NullPointerException: " + e.getMessage());
        }
        return value;
    }


    public void parsesb(StringBuilder mStringBuilder) {
        String[] rows = mStringBuilder.toString().split(":");
        for (int i = 0; i < rows.length; i++) {
            String[] cols = rows[i].split(",");
            addstudent1(cols[1],cols[5],cols[0],cols[2],cols[3],cols[4]);
        }

    }
    public void addstudent1(String ena,String esno,String ero,String edi,String rdb ,String ebt)
    {
        DatabaseHelper dbh =new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ROLLNO, ero);
        contentValues.put(DatabaseHelper.SNAME,ena);
        contentValues.put(DatabaseHelper.SEATNO, esno);;
        contentValues.put(DatabaseHelper.DIV, edi);
        contentValues.put(DatabaseHelper.BATCHNO, ebt);
        contentValues.put(DatabaseHelper.RKT, rdb);
        try
        {
            long id = db.insertOrThrow(DatabaseHelper.STUDENT, null, contentValues);
            Log.i("parent",Long.toString(id));
        }
        catch(android.database.SQLException e)
        {
            // Sep 12, 2013 6:50:17 AM
            Log.e("Exception","SQLException"+String.valueOf(e.getMessage()));
            e.printStackTrace();
        }


    }
}


