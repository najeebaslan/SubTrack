package com.najeeb.movies.screens.home

import android.accounts.Account
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.najeeb.movies.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmailCard(
  email: Email,
  onClick: (Long) -> Unit,
  onLongClick: (Long) -> Unit,
  modifier: Modifier = Modifier,
  isSelected: Boolean = false,
) {
  OutlinedCard(
    modifier = modifier
      .padding(horizontal = 16.dp, vertical = 4.dp)
      .semantics { selected = isSelected }
      .clip(CardDefaults.shape)
      .combinedClickable(
        onClick = { onClick(email.id) },
        onLongClick = { onLongClick(email.id) }
      )
      .border(
        1.dp,
        MaterialTheme.colorScheme.surface.copy(alpha = 0.1f),
        MaterialTheme.shapes.medium
      ),

    colors = CardDefaults.cardColors(
      containerColor = when {
        isSelected -> MaterialTheme.colorScheme.primaryContainer
        else -> MaterialTheme.colorScheme.surfaceVariant
      }
    )
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
    ) {

      Row(modifier = Modifier.fillMaxWidth()) {
        val clickModifier = Modifier.clickable(
          interactionSource = remember { MutableInteractionSource() },
          indication = null
        ) {
          onLongClick(email.id)
        }

        AnimatedContent(targetState = isSelected, label = "AvatarSwitch") {
          if (it) {
            SelectedProfileImage(modifier = clickModifier)
          } else {
            ReplyProfileImage(
              avatarUrl = email.sender.avatar,
              modifier = clickModifier
            )
          }
        }

        Column(
          modifier = Modifier
            .weight(1f)
            .padding(horizontal = 12.dp, vertical = 4.dp),
          verticalArrangement = Arrangement.Center
        ) {
          Text(
            text = email.sender.name,
            style = MaterialTheme.typography.labelMedium
          )
          Text(
            text = email.createdAt,
            style = MaterialTheme.typography.labelMedium
          )
        }
        IconButton(
          onClick = { /*TODO*/ },
          modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceContainerHigh),
        ) {
          Icon(
            painter = painterResource(id = R.drawable.ic_star_border),
            contentDescription = "Favorite",
            tint = MaterialTheme.colorScheme.outline,
          )
        }
      }

      Text(
        text = email.subject,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
      )
      Text(
        text = email.body,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}

@Composable
fun SelectedProfileImage(modifier: Modifier = Modifier) {
  Box(
    modifier
      .size(40.dp)
      .clip(CircleShape)
      .background(MaterialTheme.colorScheme.primary),
  ) {
    Icon(
      painter = painterResource(id = R.drawable.ic_check),
      contentDescription = null,
      modifier = Modifier
        .size(24.dp)
        .align(Alignment.Center),
      tint = MaterialTheme.colorScheme.onPrimary,
    )
  }
}

@Composable
fun ReplyProfileImage(avatarUrl: Int, modifier: Modifier = Modifier) {
  Image(
    modifier = modifier
      .size(40.dp)
      .clip(CircleShape),
    painter = painterResource(id = avatarUrl),
    contentDescription = null,
  )
}

enum class MailboxType {
  INBOX, SENT, DRAFT, SPAM, TRASH
}

data class Accounts(
  val id: Long,
  val name: String,
  val email: String,
  val avatar: Int // Resource ID (e.g. R.drawable.avatar)
)

data class EmailAttachment(
  val id: Long,
  val fileName: String,
  val fileSize: String
)

data class Email(
  val id: Long,
  val sender: Accounts,
  val recipients: List<Account> = emptyList(),
  val subject: String,
  val body: String,
  val attachments: List<EmailAttachment> = emptyList(),
  var isStarred: Boolean = false,
  var mailbox: MailboxType = MailboxType.INBOX,
  val createdAt: String,
  val threads: List<Email> = emptyList(),
)


val mockEmails = listOf(
  Email(
    id = 1L,
    sender = Accounts(
      id = 1L,
      name = "John Doe",
      email = "john.doe@example.com",
      avatar = R.drawable.image_profile
    ),
    recipients = listOf(

    ),
    subject = "Meeting Reminder",
    body = "Don't forget about our meeting scheduled for tomorrow at 10 AM.",
    attachments = listOf(
      EmailAttachment(
        id = 1L,
        fileName = "agenda.pdf",
        fileSize = "250KB"
      )
    ),
    isStarred = false,
    mailbox = MailboxType.INBOX,
    createdAt = "2025-07-30 08:45 AM",
  ),

  Email(
    id = 2L,
    sender = Accounts(
      id = 3L,
      name = "Support Team",
      email = "support@example.com",
      avatar = R.drawable.image_profile
    ),
    subject = "Your Ticket Has Been Resolved",
    body = "Hello, your support ticket has been successfully resolved.",
    attachments = emptyList(),
    isStarred = true,
    mailbox = MailboxType.INBOX,
    createdAt = "2025-07-29 04:20 PM",
  ),

  Email(
    id = 3L,
    sender = Accounts(
      id = 4L,
      name = "Marketing",
      email = "promo@company.com",
      avatar = R.drawable.image_profile
    ),
    subject = "🎉 50% Off Sale - This Week Only!",
    body = "Take advantage of our mid-year sale before it ends!",
    attachments = listOf(
      EmailAttachment(
        id = 2L,
        fileName = "offer.jpg",
        fileSize = "1.2MB"
      )
    ),
    isStarred = false,
    mailbox = MailboxType.SPAM,
    createdAt = "2025-07-28 12:00 PM",
  )
)

