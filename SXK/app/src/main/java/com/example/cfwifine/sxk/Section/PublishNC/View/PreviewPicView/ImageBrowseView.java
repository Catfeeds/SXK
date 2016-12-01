package com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView;

import android.content.Context;
import android.content.Intent;

import java.util.List;

public interface ImageBrowseView {

    Intent getDataIntent();

    void setImageBrowse(List<String> images, int position);

    Context getMyContext();
}
