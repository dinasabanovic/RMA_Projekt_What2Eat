package com.example.what2eat.model

data class MUser(val id: String?,
                 val userId: String,
                 val displayName: String,
                 val avatarUrl: String,
                 val profession: String) {
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "user_id" to this.userId,
            "display_name" to this.displayName,
            "profession" to this.profession,
            "avatar_url" to this.avatarUrl
        )
    }
}
