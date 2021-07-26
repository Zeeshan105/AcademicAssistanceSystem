package comp3350.AAS.presentation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import comp3350.AAS.R;

public class CardViewHolder {
    ImageView itemImage;
    TextView cardTitle;
    TextView cardDesc;

    CardViewHolder(View v){
        itemImage = v.findViewById(R.id.imageViewFlashCard);
        cardTitle = v.findViewById(R.id.textViewCardTitle);
        cardDesc = v.findViewById(R.id.textViewCardDescription);
    }
}
