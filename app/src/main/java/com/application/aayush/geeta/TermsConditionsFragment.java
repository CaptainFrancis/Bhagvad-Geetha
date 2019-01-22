package com.application.aayush.geeta;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TermsConditionsFragment extends Fragment {

    TextView text1,text2;
    Toolbar toolbar;
    ImageButton back;
    String name,mobile_number,email,address,city;
    Button ok ;
    public TermsConditionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_terms_conditions, container, false);
        toolbar = (Toolbar)view.findViewById(R.id.my_toolbar);
        back = (ImageButton) view.findViewById(R.id.button41);
        name = getArguments().getString("user_name");
        mobile_number = getArguments().getString("user_mobilenumber");
        email = getArguments().getString("user_email");
        address = getArguments().getString("user_address");
        city = getArguments().getString("user_city");
        ok = (Button)view.findViewById(R.id.button10);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().show();
                getActivity().onBackPressed();

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        text1 = (TextView)view.findViewById(R.id.textView22);
        text2= (TextView)view.findViewById(R.id.textView33);
        Typeface customFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Lato-Regular.ttf");
        text1.setTypeface(customFont);
        text2.setTypeface(customFont);
        text1.setText("Life meanders through the dense perceptions of time, hurried and unhurried, filling up the jars of our senses, overflowing on some days, and vacuously empty and numb on others while we breathe. There have always been reasons to celebrate as much as reasons to soak in a gloomy cloud, and they are all present around us, some near and some distant, we just have to walk to the ones we feel are right for us. This selection, though of right and wrong is not as mighty tough as is the struggle to take possession of  the knowledge of right and wrong. We tend to factorise and brutally break situations to rearrange them in a puzzle that creates the “best for us”. We love the black and white and completely ignore the colours of our emotions, partly because there are so many of them, nameless, and partly because, naming them would mean, feeling the unknown.\n" +
                "\n" +
                "Feelings are often corrupted by words and by questions we keep asking ourselves. Writers try to give voices to these feelings, but are rarely satisfied, hopelessly giving their works to the publishers, not wanting to part with the process of expression accurately, their never ending struggle to perfectly describe their emotions and ascribe words to them. \n");
        text1.setTextColor(getResources().getColor(R.color.colorblack));
        text2.setText("In our times, we struggle in our mornings and nights, deciding between several tabs of our web browser, or between several flavours of green tea. Our rational upbringing, forces us to evaluate the pros and cons of every prospective partner, or friend. We have become so careful and yet so unaware of ourselves. We love, but in so many ways that if love was a person, she would like us feel the crisis of her own identity.\n" +
                "\n" +
                "We have grown up becoming managers, problem solvers, living in so many variables and trying to juggle everything with only two hands. Childhood in these times has become not more than mere ‘learning management’ and learning with scores of subjects, trying to make some real estate in the child’s hope to be secure future. Our adolescence is busy in finding others, and not ourselves, seeing a different version of ourselves in everyone, accepting neither, lost and often broken. Our twenties are occupied with the quest for wealth making, hoping that it is the answer to every problem, is the soil where happiness sprouts. We often dirty our souls in the process with guilt of betrayal and jealousy. Soon, life gives life and we impose our unfulfilled dreams on our children, sparking their wicks with ambition, making them into us, an ever expanding big bang of desires.\n" +
                "\n" +
                "Why then do we need rest? Why then, when everything is so well ‘sorted’ and ‘figured out’ we need an anchor? S. Radhakrishnan says that “it (Geetha) is not an esoteric work designed for and understood by the specially initiated but a popular poem which helps even those ‘who wander in the region of many variables’.\n" +
                "\n" +
                "Let us read the Geetha together and interpret it with the knowledge of our ever changing world. Let us find ourselves victorious over our inner evil. The most beautiful part of this victory is that everyone can win.\n");
        text2.setTextColor(getResources().getColor(R.color.colorblack));
        return view;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

    }
}
