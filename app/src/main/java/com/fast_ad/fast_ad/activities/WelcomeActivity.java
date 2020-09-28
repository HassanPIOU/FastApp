package com.fast_ad.fast_ad.activities;

import android.content.Intent;
import android.os.Bundle;

import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.Utils.Helper;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends FancyWalkthroughActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_welcome);

        if (Helper.restorePrefData(getApplicationContext())) {
            Intent start = new Intent(getApplicationContext(), SplashActivity.class);
            startActivity(start);
            finish();
        }
        FancyWalkthroughCard fancywalkthroughCard1 = new FancyWalkthroughCard("Trouvez votre Articles", "Trouvers votre article dans n'importe quel magazin ou restaurant de votre ville");
        FancyWalkthroughCard fancywalkthroughCard2 = new FancyWalkthroughCard("Commandez votre article", "Commandez les articles de n'importe quel magazin ou restaurant de votre ville");
        FancyWalkthroughCard fancywalkthroughCard3 = new FancyWalkthroughCard("Notre coursier s'en charge", "Le coursier achetera les produits. pour vous");
        FancyWalkthroughCard fancywalkthroughCard4 = new FancyWalkthroughCard("Nous vous livrons", "Attendez patiemment  que notre coursier apporte votre repas à votre porte.");

        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        fancywalkthroughCard1.setIconLayoutParams(300,300,0,0,0,0);
        fancywalkthroughCard2.setBackgroundColor(R.color.white);
        fancywalkthroughCard2.setIconLayoutParams(300,300,0,0,0,0);
        fancywalkthroughCard3.setBackgroundColor(R.color.white);
        fancywalkthroughCard3.setIconLayoutParams(300,300,0,0,0,0);
        fancywalkthroughCard4.setBackgroundColor(R.color.white);
        List<FancyWalkthroughCard> pages = new ArrayList<>();

        pages.add(fancywalkthroughCard1);
        pages.add(fancywalkthroughCard2);
        pages.add(fancywalkthroughCard3);
        pages.add(fancywalkthroughCard4);

        for (FancyWalkthroughCard page : pages) {
            page.setTitleColor(R.color.white);
            fancywalkthroughCard1.setBackgroundColor(R.color.black);
            fancywalkthroughCard2.setBackgroundColor(R.color.black);
            fancywalkthroughCard3.setBackgroundColor(R.color.black);
            fancywalkthroughCard4.setBackgroundColor(R.color.black);
            page.setDescriptionColor(R.color.white);
        }
        setFinishButtonTitle("Commencer");
        showNavigationControls(true);
      // setColorBackground(R.color.colorPrimary);
         setImageBackground(R.drawable.restaurant);
       // setInactiveIndicatorColor(R.color.grey_600);
      //  setActiveIndicatorColor(R.color.colorPrimary);
        setOnboardPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {
        Intent start = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(start);
        finish();
    }
}
