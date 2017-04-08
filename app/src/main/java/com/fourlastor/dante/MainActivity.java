package com.fourlastor.dante;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spanned;
import android.widget.TextView;

import com.fourlastor.dante.html.FlavoredHtml;
import com.fourlastor.dante.html.ImgGetter;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView html;
    private FlavoredHtml flavoredHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        html = ((TextView) findViewById(R.id.html));

        flavoredHtml = new FlavoredHtml.Builder(this)
                .newLine("p", "h1", "h2", "h3", "h4", "h5", "h6", "li")
                .textAppearance(R.style.headline, "h1")
                .textAppearance(R.style.title, "h2")
                .textAppearance(R.style.subhead, "h3")
                .textAppearance(R.style.body, "p", "li")
                .style(Typeface.BOLD, "b", "strong")
                .style(Typeface.ITALIC, "i", "em")
                .bullet(15, "li")
                .img(new ImgGetter.BitmapImgGetter(getResources()) {
                    @Override
                    protected Bitmap getBitmap(String src) {
                        try {
                            return Picasso.with(MainActivity.this)
                                    .load(src)
                                    .get();
                        } catch (IOException e) {
                            throw new RuntimeException("Whoops!");
                        }
                    }
                })
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();

        final String rawHtml = "<h1>HTML Ipsum Presents</h1>\n" +
                "\n" +
                "<p><strong>Pellentesque habitant morbi tristique</strong> senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. <em>Aenean ultricies mi vitae est.</em> Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, <code>commodo vitae</code>, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum, eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. <a href=\"#\">Donec non enim</a> in turpis pulvinar facilisis. Ut felis.</p>\n" +
                "\n" +
                "<h2>Header Level 2</h2>\n" +
                "\n" +
                "<ol>\n" +
                "   <li>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.</li>\n" +
                "   <li>Aliquam tincidunt mauris eu risus.</li>\n" +
                "</ol>\n" +
                "\n" +
                "<blockquote><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus magna. Cras in mi at felis aliquet congue. Ut a est eget ligula molestie gravida. Curabitur massa. Donec eleifend, libero at sagittis mollis, tellus est malesuada tellus, at luctus turpis elit sit amet quam. Vivamus pretium ornare est.</p></blockquote>\n" +
                "\n" +
                "<p><img src='https://github.com/fourlastor/dante/raw/master/images/screencast.gif' /></p>" +
                "\n" +
                "<h3>Header Level 3</h3>\n" +
                "\n" +
                "<ul>\n" +
                "   <li>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.</li>\n" +
                "   <li>Aliquam tincidunt mauris eu risus.</li>\n" +
                "</ul>";

        new AsyncTask<Void, Void, Spanned>() {

            @Override
            protected Spanned doInBackground(Void... params) {
                return flavoredHtml.parse(rawHtml);
            }

            @Override
            protected void onPostExecute(Spanned spanned) {
                html.setText(spanned);
            }
        }.execute();
    }
}
