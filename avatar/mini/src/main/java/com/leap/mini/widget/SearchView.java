package com.leap.mini.widget;

import com.leap.mini.R;
import com.leap.mini.util.IsEmpty;
import com.leap.mini.util.KeyBoardUtil;
import com.leap.mini.widget.cleartextfield.ClearEditText;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 搜索输入框
 * <p>
 * </> Created by weiyaling on 2017/4/26.
 */

public class SearchView extends LinearLayout {
  private OnSearchListener<View, String> searchListener;
  private int limit;
  private ClearEditText clearEditText;
  private AttributeSet attrs;
  private String hint;

  public SearchView(Context context) {
    super(context);
    initView();
  }

  public SearchView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.attrs = attrs;
    initView();
  }

  public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.attrs = attrs;
    initView();
  }

  private void initView() {
    View view = LayoutInflater.from(getContext()).inflate(R.layout.item_search, null);
    LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT);
    addView(view, lp);
    clearEditText = (ClearEditText) findViewById(R.id.select_et);
    if (attrs != null) {
      TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.searchView);
      hint = array.getString(R.styleable.searchView_hint);
      limit = array.getInteger(R.styleable.searchView_limit, 20);
      array.recycle();
    }
    setHint(hint);
    setLimit(limit);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    createEventHandlers();
  }

  private void createEventHandlers() {
    clearEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        KeyBoardUtil.keyShow(clearEditText, false);
        if (!IsEmpty.object(searchListener))
          searchListener.onSearch(clearEditText, getText());
        return false;
      }
    });
  }

  public void setSearchListener(OnSearchListener<View, String> confirmListener) {
    this.searchListener = confirmListener;
  }

  public void setHint(String hint) {
    this.hint = hint;
    clearEditText.setHint(hint);
  }

  public void setText(String text) {
    clearEditText.setText(text);
    clearEditText.setSelection(text.length());
  }

  public void setLimit(int limit) {
    this.limit = limit;
    clearEditText.setFilters(new InputFilter[] {
        new InputFilter.LengthFilter(limit) });
  }

  public String getText() {
    return clearEditText.getText().toString().trim();
  };

  public ClearEditText getClearEditText() {
    return clearEditText;
  }

  public interface OnSearchListener<V, T> {
    void onSearch(V view, T data);
  }
}
