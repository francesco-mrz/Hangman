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
import hangman.GameResult;
import hangman.Hangman;
import hangman.Player;

/**
 *
 * @author Claudio Cusano <claudio.cusano@unipv.it>
 */
public class HangmanServer {

	/**
	 * @param args the command line arguments
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Socket socket = null;
		int port = 8888;
		Game game = new Game("Prova");

		try {
			ServerSocket serverSocket = new ServerSocket(port, 0, InetAddress.getByName(null));
			socket = serverSocket.accept();
		} catch (Exception e) {
			// TODO: handle exception
		}

		while (true) {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

//			out.write(game.getSecretWord());
//			out.write(game.getKnownLetters());
//			out.write(game.countMissingLetters());
//			out.write(game.countFailedAttempts());

			out.println(game.getSecretWord());
			

			while (game.getResult() == GameResult.OPEN) {
				out.println(game.getKnownLetters());
				out.println(game.countMissingLetters());
				out.println(game.countFailedAttempts());

				String line = in.readLine();
				char c = line.charAt(0);
				game.makeAttempt(c);

				System.out.println(game.countMissingLetters());
				System.out.println(game.countFailedAttempts());
//				out.write(game.countFailedAttempts());
//				out.write(game.countMissingLetters());
			}
		}
	}
}
