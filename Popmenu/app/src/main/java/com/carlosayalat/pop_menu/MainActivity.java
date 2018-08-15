package com.carlosayalat.pop_menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // dentro de res darle a new-> Android resource directory -> escoger opcion menu
    // dentro de la carpeta menu creada cogemos Menu resource file
    public void abrirMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.itemVer:
                        Toast.makeText(getApplicationContext(), "Ver producto",Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.itemComprar:
                        Toast.makeText(getApplicationContext(), "Comprar",Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.itemComparar:
                        Toast.makeText(getApplicationContext(), "Comparar",Toast.LENGTH_LONG).show();
                        return true;
                    default: return false;
                }
                //return false;   // retorna esto cuando no haga nada
            }
        });

        popupMenu.inflate(R.menu.pop_menu); // permite colocar cual menu desplegar
        popupMenu.show();
    }
}
