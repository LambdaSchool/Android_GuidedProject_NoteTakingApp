package com.lambdaschool.notetaker.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

import com.lambdaschool.notetaker.R;

public class LinedEditText extends android.support.v7.widget.AppCompatEditText {

    Rect  rect;
    Paint paint;
    int lineOffset;

    public LinedEditText(Context context) {
        super(context);
        init(null);
    }

    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LinedEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    protected void init(AttributeSet attrs) {
        rect = new Rect();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);

        if(attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LinedEditText);
            paint.setStrokeWidth(
                    typedArray.getInt(
                            R.styleable.LinedEditText_line_thickness,
                            2));
            paint.setColor(
                    typedArray.getColor(
                            R.styleable.LinedEditText_line_color,
                            getResources().getColor(R.color.lineBlue)));
            lineOffset = typedArray.getInt(R.styleable.LinedEditText_line_offset, 0);
        }
    }

    public int getLineOffset() {
        return lineOffset;
    }

    public void setLineOffset(int lineOffset) {
        this.lineOffset = lineOffset;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int count = getLineCount();

        for(int i = 0; i < count; ++i) {
            int baseline = getLineBounds(i, rect);
            canvas.drawLine(rect.left, baseline + lineOffset, rect.right, baseline + lineOffset, paint);
        }

        super.onDraw(canvas);
    }
}
