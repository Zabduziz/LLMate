package com.example.llmate.feature.textsummary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.llmate.components.FilledButtonExample
import com.example.llmate.components.TextCard

@Composable
fun TextSummaryScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextCard("Enter your Text")
        Spacer(modifier = Modifier.height(12.dp))
        TextCard("Summary")
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilledButtonExample(
                text = "Copy",
                modifier = Modifier.padding( 12.dp)
            ) {  }
            FilledButtonExample("Summarize") {  }
        }
    }
}