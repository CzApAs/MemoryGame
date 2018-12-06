package com.example.memorygame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MemoryGameButtonTest {

    @Test
    public void getFrontImageTest() {
        Drawable drawableImage = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getDrawable(R.drawable.anotherquestionmark, null);
        MemoryGameButton testedButton = new MemoryGameButton(InstrumentationRegistry.getInstrumentation().getTargetContext(), 0, 0, drawableImage);

        Drawable returnedImage = testedButton.getFrontImage();

        assertTrue(returnedImage.equals(testedButton.frontImage));
    }

    @Test
    public void isMatchedTest() {
        Drawable drawableImage = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getDrawable(R.drawable.anotherquestionmark, null);
        MemoryGameButton testedButton = new MemoryGameButton(InstrumentationRegistry.getInstrumentation().getTargetContext(), 0, 0, drawableImage);

        testedButton.isMatched = true;
        assertTrue(testedButton.isMatched());
    }

    @Test
    public void isNotMatchedTest() {
        Drawable drawableImage = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getDrawable(R.drawable.anotherquestionmark, null);
        MemoryGameButton testedButton = new MemoryGameButton(InstrumentationRegistry.getInstrumentation().getTargetContext(), 0, 0, drawableImage);

        testedButton.isMatched = false;
        assertFalse(testedButton.isMatched());
    }

    @Test
    public void setMatchedTrueTest() {
        Drawable drawableImage = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getDrawable(R.drawable.anotherquestionmark, null);
        MemoryGameButton testedButton = new MemoryGameButton(InstrumentationRegistry.getInstrumentation().getTargetContext(), 0, 0, drawableImage);

        testedButton.setMatched(true);
        assertTrue(testedButton.isMatched());
    }

    @Test
    public void setMatchedFalseTest() {
        Drawable drawableImage = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getDrawable(R.drawable.anotherquestionmark, null);
        MemoryGameButton testedButton = new MemoryGameButton(InstrumentationRegistry.getInstrumentation().getTargetContext(), 0, 0, drawableImage);

        testedButton.setMatched(false);
        assertFalse(testedButton.isMatched());
    }

    @Test
    public void flipAlreadyMatchedTest() {
        Drawable drawableImage = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getDrawable(R.drawable.anotherquestionmark, null);
        MemoryGameButton testedButton = new MemoryGameButton(InstrumentationRegistry.getInstrumentation().getTargetContext(), 0, 0, drawableImage);

        testedButton.isMatched=true;
        testedButton.isFlipped=true;
        testedButton.flip();
        assertTrue(testedButton.isFlipped==true);  // trying to flip an already matched image should not change its state
    }

    @Test
    public void flipAlreadyFlippedTest() {
        Drawable drawableImage = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getDrawable(R.drawable.anotherquestionmark, null);
        MemoryGameButton testedButton = new MemoryGameButton(InstrumentationRegistry.getInstrumentation().getTargetContext(), 0, 0, drawableImage);

        testedButton.isMatched=false;
        testedButton.isFlipped=true;
        testedButton.flip();
        assertTrue(testedButton.isFlipped==false);
    }

    @Test
    public void flipNotFlippedTest() {
        Drawable drawableImage = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getDrawable(R.drawable.anotherquestionmark, null);
        MemoryGameButton testedButton = new MemoryGameButton(InstrumentationRegistry.getInstrumentation().getTargetContext(), 0, 0, drawableImage);

        testedButton.isMatched=false;
        testedButton.isFlipped=false;
        testedButton.flip();
        assertTrue(testedButton.isFlipped==true);
    }
}