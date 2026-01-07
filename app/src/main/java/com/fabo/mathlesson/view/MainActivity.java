/*
 * MainActivity.java     1.82 01/03/2026
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
 *
 */package com.fabo.mathlesson.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.fabo.mathlesson.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Thread.sleep(3000L);
        setContentView(R.layout.activity_main);
    }
}