package at.sw2017xp3.regionalo.util;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import at.sw2017xp3.regionalo.R;
import at.sw2017xp3.regionalo.Regionalo;
import at.sw2017xp3.regionalo.model.Core;
import at.sw2017xp3.regionalo.model.Product;

/**
 * Created by Tobias on 24.05.2017.
 */

public class CommonUi
{
    public static void fillProductPresentation(Product p, LinearLayout inflatedView, View.OnClickListener v)
    {

        int productLayoutId = p.getId();
        LinearLayout productLayout = (LinearLayout) inflatedView.findViewById(R.id.linearLayout_product);
        (inflatedView.findViewById(R.id.linearLayout_product)).setId(productLayoutId);
        ImageButton image_load = (ImageButton) productLayout.findViewById(R.id.imageButtonProduct);
        image_load.setOnClickListener(v);
        Glide.with(Regionalo.getContext()).load(Core.getInstance().getProducts().getImageUri(p.getId())).into(image_load);
        (productLayout.findViewById(R.id.imageButtonProduct)).setOnClickListener(v);
        ((TextView) productLayout.findViewById(R.id.textViewRndProduct1)).setText(p.getName());
        ((TextView) productLayout.findViewById(R.id.textViewRndProduct2)).setText(Regionalo.getContext().getString(R.string.producerID) + String.valueOf(p.getId()));
        ((TextView) productLayout.findViewById(R.id.textViewRndProduct3)).setText(Regionalo.getContext().getString(R.string.producerID) + String.valueOf(p.getProducerId()));
        ((TextView) productLayout.findViewById(R.id.textViewRndProduct4)).setText(Regionalo.getContext().getString(R.string.productPrice) + String.valueOf(p.getPrice()));
        ((TextView) productLayout.findViewById(R.id.textViewRndProduct5)).setText(Regionalo.getContext().getString(R.string.productType) + String.valueOf(p.getType()));
    }

}
