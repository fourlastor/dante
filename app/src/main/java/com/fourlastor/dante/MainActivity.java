package com.fourlastor.dante;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spanned;
import android.widget.TextView;

import com.fourlastor.dante.html.FlavoredHtml;

public class MainActivity extends AppCompatActivity {

    private TextView html;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        html = ((TextView) findViewById(R.id.html));
    }

    @Override
    protected void onStart() {
        super.onStart();

        String rawHtml = "<h1>HTML Ipsum Presents</h1>\n" +
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
                "<h3>Header Level 3</h3>\n" +
                "\n" +
                "<ul>\n" +
                "   <li>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.</li>\n" +
                "   <li>Aliquam tincidunt mauris eu risus.</li>\n" +
                "</ul>";
        Spanned htmlText = FlavoredHtml.fromHtml(this, rawHtml);

        html.setText(htmlText);
    }
}
