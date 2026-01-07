/*
 * BarChartCustomView.java     1.82 01/03/2026
 *
 * Copyright (c) 2025-2026 Francis A Burns.
 * 1140 E Marlin Dr. Chandler, Arizona 85286, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Francis A Burns. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Francis.
 */
package com.fabo.mathlesson.view; // Replace with your package name

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class BarChartCustomView extends View {

    private Paint correctBarPaint, incorrectBarPaint, timeBarPaint, totalBarPaint;
    private Paint axisPaint, textPaint;
    private List<Float> dataPoints;
    private ArrayList<Paint> barColors;
    private ArrayList<String> barText;


    private float maxValue = 0;

    public BarChartCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        barText = new ArrayList<>();
        barColors = new ArrayList<>();

        correctBarPaint = new Paint();
        correctBarPaint.setColor(Color.GREEN);
        correctBarPaint.setStyle(Paint.Style.FILL);
        barText.add("Correct");
        barColors.add(correctBarPaint);

        incorrectBarPaint = new Paint();
        incorrectBarPaint.setColor(Color.RED);
        incorrectBarPaint.setStyle(Paint.Style.FILL);
        barText.add("Wrong");
        barColors.add(incorrectBarPaint);

        totalBarPaint = new Paint();
        totalBarPaint.setColor(Color.CYAN);
        totalBarPaint.setStyle(Paint.Style.FILL);
        barText.add("Total");
        barColors.add(totalBarPaint);

        timeBarPaint = new Paint();
        timeBarPaint.setColor(Color.BLUE);
        timeBarPaint.setStyle(Paint.Style.FILL);
        barText.add("Avg Time");
        barColors.add(timeBarPaint);

        axisPaint = new Paint();
        axisPaint.setColor(Color.BLACK);
        axisPaint.setStrokeWidth(8f);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(50f);

        dataPoints = new ArrayList<>();
    }

    public void setData(List<Float> data) {
        this.dataPoints = data;
        maxValue = 0;
        for (Float value : data) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        // Request the view to be redrawn
        invalidate(); 
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (dataPoints.isEmpty()) {
            return;
        }

        int width = getWidth();
        int height = getHeight();
        int padding = 50;
        int barSpacing = 50;
        float availableWidth = width - 2 * padding;
        float barWidth = availableWidth / dataPoints.size() - barSpacing;
        float usableHeight = height - 2 * padding;

        // Draw X and Y axes (simple lines for illustration)
        canvas.drawLine(padding, height - padding, width - padding, height - padding, axisPaint); // X-axis
        canvas.drawLine(padding, height - padding, padding, padding, axisPaint); // Y-axis

        for (int i = 0; i < dataPoints.size(); i++) {
            float value = dataPoints.get(i);
            // Calculate bar height relative to max value
            float barHeight = (value / maxValue) * usableHeight;
            float left = padding + i * (barWidth + barSpacing) + (float) barSpacing / 2;
            float top = height - padding - barHeight;
            float right = left + barWidth;
            float bottom = height - padding;

            // Draw the bar
            canvas.drawRect(left, top, right, bottom, barColors.get(i));

            // Draw the value text above the bar
            canvas.drawText(String.valueOf(value), left, top - 10, textPaint);
            canvas.drawText(barText.get(i), left, bottom +50, textPaint);
        }
    }
}

