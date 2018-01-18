package com.cubolabs.bibliaofflinearc.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.view.ActionMode;
import android.text.Html;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cubolabs.bibliaofflinearc.R;

import org.jsoup.Jsoup;

import greendao.Verse;

public class ContextualActionBar implements ActionMode.Callback {
    private VersiculosFragment versiculosFragment;
    private Activity activity;
    private int nr = 0;

    public ContextualActionBar(VersiculosFragment versiculosFragment) {
        this.versiculosFragment = versiculosFragment;
        this.activity = versiculosFragment.getActivity();
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        Boolean versesBottom = preferences.getBoolean("versesbottom_preference", false);

        StringBuilder versesToShare = new StringBuilder();
        StringBuilder versesToShareHtml = new StringBuilder();
        int len = versiculosFragment.getListView().getCount();
        SparseBooleanArray checked = versiculosFragment.getListView().getCheckedItemPositions();
        for (int i = 0; i < len; i++){
            if (checked.get(i)) {
                Verse verse = (Verse) versiculosFragment.getListAdapter().getItem(i);
                if(!versesBottom)
                    versesToShare.append(" " + String.valueOf(i+1) + ". ");
                else
                    versesToShare.append(" ");

                versesToShare.append(Jsoup.parse(verse.getText()).text());
                versesToShare.append("\n");

                if(!versesBottom)
                    versesToShareHtml.append("<b>"+String.valueOf(i+1) + ". </b>");
                else
                    versesToShareHtml.append(" ");

                versesToShareHtml.append(verse.getText());
                versesToShareHtml.append("<br>");
            }
        }
        versesToShare.append("\n");
        versesToShare.append(versiculosFragment.selectedVersesTitle());
        versesToShareHtml.append("<br>");
        versesToShareHtml.append(versiculosFragment.selectedVersesTitle());

        switch (item.getItemId()) {
            case R.id.menu_item_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, versiculosFragment.currentChapter());
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "\n"+versesToShare.toString());
                sharingIntent.putExtra(Intent.EXTRA_HTML_TEXT, versesToShareHtml.toString());
                versiculosFragment.startActivity(Intent.createChooser(sharingIntent, "Enviar via"));
                break;
            case R.id.menu_item_copy:
                copyToClipboard(versesToShare.toString(), versesToShareHtml.toString());
                Toast.makeText(versiculosFragment.getActivity(),
                        "Copiado com sucesso!",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.d(VersiculosFragment.TAG, "onActionItemClicked: Unknown Menu Item Received!");
                mode.finish();
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        versiculosFragment.getActivity().getMenuInflater()
                .inflate(R.menu.listcab_menu, menu);
        return true;

        //Initialize the ShareActionProvider
        /*MenuItem shareMenuItem = menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareMenuItem);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "test message");
        mShareActionProvider.setShareIntent(shareIntent);
        return true;*/
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        //Nullify the actionMode object
        //so that the onClickListener can identify whether the ActionMode is ON
        versiculosFragment.actionMode = null;

        //Uncheck all checked messages
        SparseBooleanArray selectedItems = versiculosFragment.getListView().getCheckedItemPositions();
        for(int i=0;i<selectedItems.size();i++){
            versiculosFragment.getListView().setItemChecked(selectedItems.keyAt(i), false);
        }
        nr = 0;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void copyToClipboard(String extraText, String extraHtmlText) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB ) {
            final android.text.ClipboardManager clipboard =
                    (android.text.ClipboardManager)
                            versiculosFragment.getActivity().getSystemService(
                                    versiculosFragment.getActivity().CLIPBOARD_SERVICE);
            clipboard.setText(extraText);
        }
        else {
            ClipboardManager clipboard = (ClipboardManager)
                    versiculosFragment.getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newHtmlText("Verses clipboard",
                    extraText,
                    extraHtmlText);
            clipboard.setPrimaryClip(clip);
        }
    }
}
