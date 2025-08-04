package com.najeeb.movies.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ToggleButton(
  modifier: Modifier = Modifier,
  height: Dp,
  selectedIndex: Int,
  onToggle: (Int) -> Unit = {},
  leftDescription: String,
  rightDescription: String,
  toggleColor: Color,
  toggleBackgroundColor: Color,
  toggleBorderColor: Color,
  inactiveTextColor: Color,
  activeTextColor: Color,
) {
  val shape = RoundedCornerShape(50.dp)

  BoxWithConstraints(
    modifier = modifier
      .fillMaxWidth()
      .height(height)
      .clip(shape)
      .background(toggleBackgroundColor)
      .border(1.dp, toggleBorderColor, shape)
      .padding(5.dp)
  ) {
    val toggleWidth = maxWidth / 2
    val animatedOffsetX by animateDpAsState(
      targetValue = if (selectedIndex == 0) 0.dp else toggleWidth,
      label = "ToggleOffset"
    )

    val leftTextColor by animateColorAsState(
      targetValue = if (selectedIndex == 0) activeTextColor else inactiveTextColor,
      label = "LeftColor"
    )
    val rightTextColor by animateColorAsState(
      targetValue = if (selectedIndex == 1) activeTextColor else inactiveTextColor,
      label = "RightColor"
    )

    Box(
      modifier = Modifier
        .offset(x = animatedOffsetX)
        .width(toggleWidth)
        .fillMaxHeight()
        .clip(shape)
        .background(toggleColor)
    )

    Row(Modifier.fillMaxSize()) {
      Box(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight()
          .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
          ) {
            if (selectedIndex != 0) {
              onToggle(0)
            }
          },
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = leftDescription,
          color = leftTextColor,
          style = MaterialTheme.typography.bodyMedium.copy(
            color = rightTextColor
          ),
        )
      }

      Box(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight()
          .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
          ) {
            if (selectedIndex != 1) onToggle(1)
          },
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = rightDescription,

          style = MaterialTheme.typography.bodyMedium.copy(
            color = rightTextColor,
//            fontWeight = FontWeight.W400,

            ),
        )
      }
    }
  }
}
