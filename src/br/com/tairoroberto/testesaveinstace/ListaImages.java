package br.com.tairoroberto.testesaveinstace;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaImages implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4123640836L;
	public ArrayList<Imagem> images;
	public static final String key = "images";

	public ListaImages(ArrayList<Imagem>images) {
		// TODO Auto-generated constructor stub
		this.images = images;
	}
}
