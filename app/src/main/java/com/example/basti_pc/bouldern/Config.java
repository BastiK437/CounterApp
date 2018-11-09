package com.example.basti_pc.bouldern;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Config {
    String filesDir;
    String configPath;
    File configFile;

    public Config(String filesDir){
        this.filesDir = filesDir;
        boolean delete = false;

        configPath = filesDir + "/config.txt";
        configFile = new File(configPath);

        if(delete){
            /*
            try {
                PrintWriter pw = new PrintWriter(new FileWriter( configFile) );
                pw.printf("[Config File]\n");

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            */
            configFile.delete();
        }
    }


    public boolean exists(){
        return configFile.exists();
    }

    public void initializeConfig(){
        try {
            PrintWriter pw = new PrintWriter(new FileWriter( configFile) );
            pw.printf("[Config File]\n");
            pw.printf("Counter = 0\n");
            pw.printf("Jahresbeitrag = 0\n");


            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void safeConfig(){
    }

    public ArrayList<ConfigObj> readData(){
        ArrayList<ConfigObj> configList = new ArrayList<ConfigObj>();


        try{
            BufferedReader fos = new BufferedReader(new FileReader(configFile) );
            String tmp = "";

            while( (tmp = fos.readLine() ) != null) {
                int cnt = 0;
                String dataType = "";
                String data = "";
                while( cnt < tmp.length() && tmp.charAt(cnt)!= ' ' ){
                    dataType += String.format("%c", tmp.charAt(cnt) );
                    cnt++;
                }
                cnt += 3;
                while( cnt < tmp.length() && tmp.charAt(cnt)!= ' ' ){
                    data += String.format("%c", tmp.charAt(cnt) );
                    cnt++;
                }

                ConfigObj tmpConfig = new ConfigObj(dataType, data);
                configList.add( tmpConfig );
            }


            fos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
            Log.i("MyApp", "FileNotFoundException");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("MyApp", "IOException");
        }
        return configList;
    }

    public void writeData(ConfigObj config){
        try{
            String tmp;
            BufferedReader fos = new BufferedReader(new FileReader(configFile));
            boolean done = false;

            while( (tmp = fos.readLine() ) != null){
                ArrayList<String> tmpList = new ArrayList<>();
                tmpList.add(tmp);
                if( tmp.contains(config.getDataType()) ){
                    PrintWriter pw = new PrintWriter(new FileWriter(configFile) );

                    while( (tmp = fos.readLine() ) != null ){
                        tmpList.add(tmp);
                    }

                    for(int i=0; i<tmpList.size(); i++){
                        if( tmpList.get(i).contains(config.getDataType() ) ){
                            tmpList.remove(tmpList.get(i));
                        }
                    }


                    for( String s: tmpList ){
                        pw.printf("%s\n", s);
                    }

                    pw.printf("%s = %s\n", config.getDataType(), config.getData());
                    Log.i("MyApp", "Hat erkannt, dass " + config.getDataType() + " schon existiert" );


                    pw.close();
                    fos.close();

                    done = true;
                    break;
                }
            }

            if( !done ){
                PrintWriter pw = new PrintWriter(new FileWriter(configFile, true) );
                pw.printf("%s = %s\n", config.getDataType(), config.getData() );

                Log.i("MyApp", "Hat es nicht erkannt" );
                pw.close();
            }

        }catch (IOException e){
            e.printStackTrace();
            Log.i("MyApp", e.getMessage() + " Write Exception " + configFile.toString() );
        }
    }

    public void printConfig(){
        Log.i("MyApp", "Hallo");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(configPath) );
            String tmp = "";
            while( (tmp = reader.readLine() ) != null ){
                Log.i("MyApp", tmp);
                //System.out.printf("%s\n", tmp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("MyApp", "print FileNotFound");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("MyApp", "print IO");
        }
    }
}
