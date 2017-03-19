package com.company;


<<<<<<< HEAD
<<<<<<< b0a032290b1302496096df14a414cb6e44e66031
<<<<<<< f3b1064a555a12f357a5ba75e38487cbb1e0ca94
import java.io.*;
import java.util.ArrayList;

=======
>>>>>>> Mudanças grandes em todas as estruturas e na forma como o projecto está organizado em geral
=======
import java.io.*;
import java.util.ArrayList;

>>>>>>> (Tiago) Funções de leitura e escrita no ficheiro
=======
import java.io.*;
import java.util.ArrayList;

>>>>>>> 02c5b8b21e07c5f94facb52962a2a821ffe6867c
public class Main {

    public static void main(String[] args) {
        double[] LL = {10,13,5,17,4,6,3,2};
        double[] RR = {10,13,5,17,4,6,7,8};
        double[] LR = {10,13,5,17,4,6,2,3};
        double[] RL = {10,13,5,17,4,6,8,7};
        double[] test = {17,5,2,11,9,16,7,8,35,29,38};
        double[] test1 = {5,3,4};

        BinaryTree binaryTree = new BinaryTree(RL);
        System.out.println(binaryTree.toString());
    }

    public static void writeValues(ArrayList<ArrayList<String>> lista) throws UnsupportedEncodingException {
        try{
            Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("novo_Dados.csv"), "utf-8"));
            for ( ArrayList<String> l1 : lista) {
                for (int i=0;i<l1.size();i++){
                    if(i==l1.size()-1)
                        writer.write(l1.get(i)+"\n");
                    else
                        writer.write(l1.get(i)+";");
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("FILE NOT FOUND EXCEPTION");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<String>> readValues(){
        ArrayList<ArrayList<String>> novo = new ArrayList<ArrayList<String>>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\007\\Desktop\\projeto aed\\Projecto AED\\src\\com\\company\\dados.csv"));
            String line;
            line = br.readLine();
            String[] head=line.split(";");
            ArrayList<String> cabecalho= new ArrayList<>();
            for (String elem: head){
                cabecalho.add(elem);
            }
            novo.add(cabecalho);
            int tam=head.length;
            while ((line = br.readLine()) != null) {
                ArrayList<String> current = new ArrayList<>();
                String[] cols = line.split(";");
                for (String elem:cols){
                    current.add(elem);
                }
                novo.add(current);
            }
        } catch(IOException e) {
            System.out.println("ioexception");
        }
        return novo;
    }
<<<<<<< HEAD

    public static void writeValues(ArrayList<ArrayList<String>> lista) throws UnsupportedEncodingException {
        try{
            Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("novo_Dados.csv"), "utf-8"));
            for ( ArrayList<String> l1 : lista) {
                for (int i=0;i<l1.size();i++){
                    if(i==l1.size()-1)
                        writer.write(l1.get(i)+"\n");
                    else
                        writer.write(l1.get(i)+";");
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("FILE NOT FOUND EXCEPTION");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<String>> readValues(){
        ArrayList<ArrayList<String>> novo = new ArrayList<ArrayList<String>>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\007\\Desktop\\projeto aed\\Projecto AED\\src\\com\\company\\dados.csv"));
            String line;
            line = br.readLine();
            String[] head=line.split(";");
            ArrayList<String> cabecalho= new ArrayList<>();
            for (String elem: head){
                cabecalho.add(elem);
            }
            novo.add(cabecalho);
            int tam=head.length;
            while ((line = br.readLine()) != null) {
                ArrayList<String> current = new ArrayList<>();
                String[] cols = line.split(";");
                for (String elem:cols){
                    current.add(elem);
                }
                novo.add(current);
            }
        } catch(IOException e) {
            System.out.println("ioexception");
        }
        return novo;
    }
=======
>>>>>>> 02c5b8b21e07c5f94facb52962a2a821ffe6867c
}
