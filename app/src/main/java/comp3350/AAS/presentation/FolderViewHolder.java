package comp3350.AAS.presentation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import comp3350.AAS.R;

public class FolderViewHolder {
    ImageView itemImage;
    TextView folderName;
    TextView numCards;

    FolderViewHolder(View v){
        itemImage = v.findViewById(R.id.imageView1);
        folderName = v.findViewById(R.id.textView1);
        numCards = v.findViewById(R.id.textView2);
    }
}
