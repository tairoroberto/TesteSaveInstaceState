package br.com.tairoroberto.testesaveinstace;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity {
	private Handler handler = new  Handler();
	private ArrayList<Imagem> images = new ArrayList<Imagem>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//verifica se tem alguma instacia de estado salva
		if (savedInstanceState != null) {
			ListaImages listaImages = (ListaImages) savedInstanceState.getSerializable(ListaImages.key);
			this.images = listaImages.images;
		}
		
		//verifica de a lista de imagens esta vazia
		if (images == null || images.size() == 0) {
			buscarImagem();
		}else{
			ConstroiImages();
		}
	}
	
	
	
	//Baixa as imagesns
	public void buscarImagem() {
		//Classe handler para manipular a thead principal
		final Handler handler = new Handler();
		
		//Thread para acesso a irtenet
		new Thread(){
			public void run() {
				try {
					for (int i = 0; i < 8; i++) {
						//Conect com uma URL para pegar a imagem
						URL url = new URL("http://tairoroberto.kinghost.net/packages/images/logo.png");
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						
						//Converte os binarios em uma imagem Bitmap
						InputStream inputStream = connection.getInputStream();
						images.add(new Imagem(BitmapFactory.decodeStream(inputStream)));
					}
				
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//manipula a Thread principal para mudar o imageview
				handler.post(new Runnable() {						
					@Override
					public void run() {
						// Muda a Imagem
						ConstroiImages();
					}
				});
			}
		}.start();
		
	}
	
	//Metodo para colocar as imagens no layput
	public void ConstroiImages() {
		LinearLayout layout = (LinearLayout)findViewById(R.id.LinearLayout1);
		for (int i = 0; i < images.size(); i++) {
			ImageView imageView = new ImageView(getBaseContext());
			imageView.setAdjustViewBounds(true);
			imageView.setImageBitmap(images.get(i).bitmap);
			layout.addView(imageView);
		}
		
	}
	
	// Salva o estado da aplicação
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putSerializable(ListaImages.key, new ListaImages(images));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
