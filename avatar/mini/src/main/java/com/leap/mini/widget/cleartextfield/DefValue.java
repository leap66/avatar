package com.leap.mini.widget.cleartextfield;

import com.leap.mini.R;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

enum DefValue {
  TXT_SIZE(36), TXT_COLOR(R.color.text_black), RIGHT_IMG(R.mipmap.ic_global_clear), BACKGROUND(
      0), HINT_COLOR(R.color.text_grey);

  int value;

  DefValue(int value) {
    this.value = value;
  }
}
