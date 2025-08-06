package com.najeeb.movies.core

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")
private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")

object SettingsStore {
  fun isOnboardingCompleted(context: Context): Flow<Boolean> =
    context.dataStore.data.map { prefs -> prefs[ONBOARDING_COMPLETED] ?: false }

  suspend fun setOnboardingCompleted(context: Context, completed: Boolean) {
    context.dataStore.edit { prefs ->
      prefs[ONBOARDING_COMPLETED] = completed
    }
  }
}
