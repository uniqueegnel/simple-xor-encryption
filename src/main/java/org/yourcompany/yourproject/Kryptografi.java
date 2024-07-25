/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 package org.yourcompany.yourproject;
 import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.Scanner;
 
 /**
  * Kelas Kryptografi untuk melakukan enkripsi dan dekripsi menggunakan metode XOR.
  * Penulis: FEUNJ3
  */
 public class Kryptografi {
 
     /**
      * Metode untuk mengenkripsi teks menggunakan kunci dan menyimpan hasilnya dalam file.
      * @param plaintext teks asli yang akan dienkripsi
      * @param key kunci untuk enkripsi
      * @param outputFile nama file untuk menyimpan ciphertext
      * @param outputFilePlain nama file untuk menyimpan plaintext yang diinput
      * @throws IOException jika terjadi kesalahan I/O
      */
     public static void encrypt(String plaintext, String key, String outputFile, String outputFilePlain) throws IOException {
         FileWriter fout = new FileWriter(outputFile);
         FileWriter foutPlain = new FileWriter(outputFilePlain);
         int n = key.length();
         int i = 0;
 
         for (char P : plaintext.toCharArray()) {
             foutPlain.write(P);
             char C = (char)(P ^ key.charAt(i));  // operasi XOR
             fout.write(C);
             i++;
             if (i >= n) i = 0;
         }
         foutPlain.close();
         fout.close();
     }
 
     /**
      * Metode untuk mendekripsi teks menggunakan kunci dan menyimpan hasilnya dalam file.
      * @param ciphertext teks terenkripsi yang akan didekripsi
      * @param key kunci untuk dekripsi
      * @param outputFile nama file untuk menyimpan hasil dekripsi
      * @throws IOException jika terjadi kesalahan I/O
      */
     public static void decrypt(String ciphertext, String key, String outputFile) throws IOException {
         FileWriter fout = new FileWriter(outputFile);
         int n = key.length();
         int i = 0;
 
         for (char C : ciphertext.toCharArray()) {
             char P = (char)(C ^ key.charAt(i));  // operasi XOR
             fout.write(P);
             i++;
             if (i >= n) i = 0;
         }
         fout.close();
     }
 
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
 
         try {
             // Enkripsi
             System.out.print("Masukkan teks plaintext: ");
             String plaintext = scanner.nextLine();
             System.out.print("Masukkan kata kunci untuk enkripsi: ");
             String key = scanner.nextLine();
             encrypt(plaintext, key, "cipher.txt", "plain-input.txt");
             System.out.println("Enkripsi selesai. Hasil disimpan di cipher.txt dan plaintext di plain-input.txt");
 
             // Membaca cipherteks dari file
             BufferedReader fin = new BufferedReader(new FileReader("cipher.txt"));
             StringBuilder ciphertext = new StringBuilder();
             int ch;
             while ((ch = fin.read()) != -1) {
                 ciphertext.append((char) ch);
             }
             fin.close();
 
             // Output hasil enkripsi
             System.out.println("Plaintext: " + plaintext);
             System.out.println("Key: " + key);
             System.out.println("Ciphertext: " + ciphertext.toString());
 
             // Dekripsi
             System.out.print("Masukkan kata kunci untuk dekripsi: ");
             key = scanner.nextLine();
             decrypt(ciphertext.toString(), key, "plain-output.txt");
             System.out.println("Dekripsi selesai. Hasil disimpan di plain-output.txt");
 
             // Membaca hasil dekripsi dari file
             BufferedReader fin2 = new BufferedReader(new FileReader("plain-output.txt"));
             StringBuilder decryptedText = new StringBuilder();
             while ((ch = fin2.read()) != -1) {
                 decryptedText.append((char) ch);
             }
             fin2.close();
 
             // Output hasil dekripsi
             System.out.println("Decrypted text: " + decryptedText.toString());
 
         } catch (IOException e) {
             System.err.println("Terjadi kesalahan: " + e.getMessage());
         }
 
         scanner.close();
     }
 }
 