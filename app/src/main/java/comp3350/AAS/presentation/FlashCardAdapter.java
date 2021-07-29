package comp3350.AAS.presentation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.AAS.R;
import comp3350.AAS.business.AccessFolder;
import comp3350.AAS.object.CardFolder;


public class FlashCardAdapter extends ArrayAdapter<String> {
    int folderIndex = -1;
    Context context;
    int cardImage;
    ArrayList<String> cardTitles;
    ArrayList<String> cardDescriptions;

    public FlashCardAdapter(Context context, ArrayList<String> cardTitles, ArrayList<String> cardDescriptions, int cardImage,int idx) {
        super(context, R.layout.activity_single_card,R.id.textViewCardTitle,cardTitles);
        this.context = context;
        this.cardImage = cardImage;
        this.cardTitles = cardTitles;
        this.cardDescriptions = cardDescriptions;
        this.folderIndex = idx;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AccessFolder accessFolder = new AccessFolder();
        ArrayList<CardFolder> folders = new ArrayList<>();
        accessFolder.getFolderList(folders);


        View singleCard = convertView;
        CardViewHolder holder = null;

        if(singleCard == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            singleCard = layoutInflater.inflate(R.layout.activity_single_card,parent,false);
            holder = new CardViewHolder(singleCard);
            singleCard.setTag(holder);
        }else{
            holder = (CardViewHolder) singleCard.getTag();
        }
        holder.itemImage.setImageResource(cardImage);
        holder.cardTitle.setText(cardTitles.get(position));
        holder.cardDesc.setText(cardDescriptions.get(position));

        singleCard.setOnClickListener(v -> {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setTitle("Would you like to Edit or Delete this card?");
            builder1.setCancelable(true);

            builder1.setNegativeButton("Delete",((dialog, which) -> {
                accessFolder.deleteCard(folders.get(folderIndex).getFolderName(),folders.get(folderIndex).getCardTitles().get(position));

                if(cardTitles.size() -1 >= 1){
                    refresh();
                    showToast("Card Deleted.");
                }else{
                    showToast("Empty folder deleted.");
                    openFolders();

                }
                
            }));
            AlertDialog alert1 = builder1.create();
            alert1.show();
        });
        return singleCard;
    }

    public void refresh(){
        Intent intent = new Intent(context,CardListActivity.class);
        context.startActivity(intent);

    }

    public void openFolders() {
        Intent intent  = new Intent(context, FolderListActivity.class);
        context.startActivity(intent);
    }

    private void showToast(String text){
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show();
    }
}
