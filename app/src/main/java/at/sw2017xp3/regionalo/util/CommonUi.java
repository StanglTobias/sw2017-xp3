package at.sw2017xp3.regionalo.util;

import android.graphics.Region;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import at.sw2017xp3.regionalo.R;
import at.sw2017xp3.regionalo.Regionalo;
import at.sw2017xp3.regionalo.model.Core;
import at.sw2017xp3.regionalo.model.Product;

import static java.io.File.separator;

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
        ((TextView) productLayout.findViewById(R.id.textViewRndProduct2)).setText(String.valueOf(p.getPrice() + Regionalo.getContext().getString(R.string.euro)));
        ((TextView) productLayout.findViewById(R.id.textViewRndProductPrice)).setText(Regionalo.getContext().getString(R.string.space) + Regionalo.getContext().getString(R.string.per)
                + Regionalo.getContext().getString(R.string.space) + String.valueOf(p.getUnit()));
        ((TextView) productLayout.findViewById(R.id.textViewRndProduct3)).setText(String.valueOf(p.getUser().getCity()));
        if(p.isBio())
        {
            productLayout.findViewById(R.id.isBioIcon).setVisibility(View.VISIBLE);
        }

    }

}
