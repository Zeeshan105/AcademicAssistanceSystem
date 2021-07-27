package comp3350.AAS.presentation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

import comp3350.AAS.R;
import comp3350.AAS.business.AccessFolder;
import comp3350.AAS.object.CardFolder;

public class CardFolderAdapter extends ArrayAdapter<String>  {
    Context context;
    int folderImage;
    ArrayList<String> folderNames;

    public CardFolderAdapter (Context context, ArrayList<String> fNames, int folderImg){
        super(context, R.layout.activity_single_folder,R.id.textView1,fNames);
        this.context = context;
        this.folderImage = folderImg;
        this.folderNames = fNames;
    }


    @Override
    public View getView(int position,View convertView, ViewGroup parent) {

        AccessFolder accessFolder = new AccessFolder();
        ArrayList<CardFolder> folders=new ArrayList<>();
        accessFolder.getFolderList(folders);

        View singleFolder = convertView;
        FolderViewHolder holder = null;

        if(singleFolder == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            singleFolder = layoutInflater.inflate(R.layout.activity_single_folder,parent,false);
            holder = new FolderViewHolder(singleFolder);
            singleFolder.setTag(holder);
        }else{
            holder = (FolderViewHolder) singleFolder.getTag();
        }
        holder.itemImage.setImageResource(folderImage);
        holder.folderName.setText(folders.get(position).getFolderName());
        holder.numCards.setText("Number of Cards: "+folders.get(position).getCardTitles().size());

        singleFolder.setOnClickListener(v -> {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Would you like to view this folder or delete this folder?");
            builder1.setCancelable(true);

            builder1.setNegativeButton("View folder", (dialog, which) -> {
                CardListActivity.folderIndex = position;
                openCardList();
            });

            builder1.setPositiveButton("Delete folder", (dialog, which) -> {
                accessFolder.deleteFolder(position);
                refresh();
            });

            AlertDialog alert1 = builder1.create();
            alert1.show();

        });


        return singleFolder;
    }

    private void showToast(String text){
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show();
    }

    public void openCardList(){
        Intent intent = new Intent(context, CardListActivity.class);
        context.startActivity(intent);
    }

    public void openFlashCards(){
        Intent intent = new Intent(context, CardHomeActivity.class);
        context.startActivity(intent);
    }

    public void refresh(){
        Intent intent = new Intent(context,FolderListActivity.class);
        context.startActivity(intent);
    }
}
