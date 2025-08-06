package com.najeeb.movies.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.najeeb.movies.R
import com.najeeb.movies.screens.navigation.Screen

@Composable
fun IsHaveAccount(
  isSignIn: Boolean,
  onClick: () -> Unit
) {
  val alreadyHaveAccount = stringResource(id = R.string.already_have_account)
  val noHaveAccount = stringResource(id = R.string.no_have_account)
  val signUp = stringResource(id = R.string.sign_up)
  val signIn = stringResource(id = R.string.sign_in)

  val primaryColor = colorScheme.primary
  val bodyStyle = MaterialTheme.typography.bodyMedium.copy(
    letterSpacing = (-0.2).sp, color = colorScheme.outline
  )

  Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
    ClickableText(
      style = bodyStyle,
      text = buildAnnotatedString {
        append(if (!isSignIn) alreadyHaveAccount else noHaveAccount)
        append("  ")//space between text

        val tag = "ACTION"
        pushStringAnnotation(tag, tag)
        withStyle(
          SpanStyle(
            color = primaryColor,
            fontFamily = FontFamily.Default, // Replace with your custom medium font if needed
            fontWeight = FontWeight.Medium
          )
        ) {
          append(if (isSignIn) signUp else signIn)
        }
        pop()
      },
      onClick = { offset ->
        val tag = "ACTION"
        val annotations = buildAnnotatedString {
          pushStringAnnotation(tag, tag)
          pop()
        }
        if (annotations.getStringAnnotations(tag, offset, offset).isNotEmpty()) {
          onClick()
        }
      }
    )
  }
}
