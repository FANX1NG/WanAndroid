package com.fanxing.wanandroid.ui.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.ui.activity.WebViewActivity;

/**
 * @author 繁星
 */
public class ShareDialogFragment extends DialogFragment implements View.OnClickListener {


    private String mTitle;
    private String mWebUrl;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_share_dialog, null);
        TextView shareToFriend = view.findViewById(R.id.tv_share_friend);
        TextView shareToBrowser = view.findViewById(R.id.tv_share_browser);
        Bundle bundle = getArguments();
        mTitle = bundle.getString(WebViewActivity.WEB_VIEW_TITLE);
        mWebUrl = bundle.getString(WebViewActivity.WEB_VIEW_URL);
        shareToBrowser.setOnClickListener(this);
        shareToFriend.setOnClickListener(this);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                .setNegativeButton("取消", null);
        return builder.create();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_share_browser:
                //从其他浏览器打开
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                Uri content_url = Uri.parse(mWebUrl);
                intent1.setData(content_url);
                startActivity(Intent.createChooser(intent1, "请选择浏览器"));
                dismiss();
                break;
            case R.id.tv_share_friend:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "share");
                intent.putExtra(Intent.EXTRA_TEXT, "文章标题《" + mTitle + "》链接：" + mWebUrl);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, "分享到"));
                dismiss();
                break;
            default:
                break;
        }
    }
}
