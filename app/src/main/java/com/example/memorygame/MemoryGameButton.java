package com.example.memorygame;

import android.content.Context;

public class MemoryGameButton extends android.support.v7.widget.AppCompatButton {

    protected int row;
    protected int column;
    protected int frontImageId;

    protected boolean isFlipped = false;
    protected boolean isMatched = false;

    public MemoryGameButton(Context context, int row, int column, int frontImageId)
    {
        super(context);
        this.row=row;
        this.column=column;
        this.frontImageId=frontImageId;
    }
}
