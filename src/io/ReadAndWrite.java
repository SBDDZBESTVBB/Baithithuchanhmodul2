package io;

import java.io.*;
import java.util.ArrayList;


public class ReadAndWrite<E> {
    public void write(ArrayList<E> list, String url) {
        File file = new File(url);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<E> read(String url) {
        File file = new File(url);
        try {
            FileInputStream fis = new FileInputStream(url);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<E> temp = (ArrayList<E>) ois.readObject();
            ois.close();
            fis.close();
            return temp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}


