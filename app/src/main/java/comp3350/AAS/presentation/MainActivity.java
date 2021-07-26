package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Button;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.AAS.R;
import comp3350.AAS.application.Main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // starting startup
        copyDatabaseToDevice();
        Main.startUp();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //flash cards button
        Button button1 = findViewById(R.id.Button1);
        button1.setOnClickListener(v -> openFlashCards());

        //create the quizzes button
        Button button2 = findViewById(R.id.Button2);
        button2.setOnClickListener(v -> openQuizPart());
    }
    @Override
    protected void onDestroy(){
        Main.shutDown();
        super.onDestroy();
    }

    public void openFlashCards(){
        Intent intent = new Intent(this, CardHomeActivity.class);
        startActivity(intent);
    }

    public void openQuizPart(){
        Intent intent = new Intent(this, QuizHomeActivity.class);
        startActivity(intent);
    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);

        } catch (IOException ioe) {
           System.out.println(ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

}