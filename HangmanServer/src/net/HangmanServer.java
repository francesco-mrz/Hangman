/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import hangman.Game;

/**
 *
 * @author Claudio Cusano <claudio.cusano@unipv.it>
 */
public class HangmanServer {

	static int port = 8888;
	static Socket socket;
	static Game game;

	/**
	 * @param args the command line arguments
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		game = new Game("ciao");
		try {
			ServerSocket serverSocket = new ServerSocket(port, 0, InetAddress.getByName(null));
			socket = serverSocket.accept();
		} catch (Exception e) {
			// TODO: handle exception
		}

		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		while (true) {
			String line = in.readLine();
			if (line.length() == 1) {
				char c = line.charAt(0);
				System.out.println(line);
				System.out.println(c);
				game.makeAttempt(c);
			}
			if (line.length() > 1) {
				System.out.println("Inserire un carattere");
				line = in.readLine();
			}
			if (line.isEmpty()) {
				System.out.println("Vuoto");
				line = in.readLine();
			}
		}
	}
}
