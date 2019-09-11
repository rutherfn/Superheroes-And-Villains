package android.rutheford.com.superheroesandvillainscentral.Activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebViewClient;

public class WebView extends AppCompatActivity
{
    private Toolbar toolBarForWebView;
    private android.webkit.WebView webView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        Main();
    }
    public void Main(){
        toolBarForWebView = findViewById(R.id.toolBarForWebView);
        setSupportActionBar(toolBarForWebView);
        getSupportActionBar().setTitle("Superheroes And Villains Central");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("https://github.com/akabab/superhero-api/blob/master/api/glossary.md");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
