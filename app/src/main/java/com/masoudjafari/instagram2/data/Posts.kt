package com.masoudjafari.instagram2.data

import com.google.gson.annotations.SerializedName

data class Posts(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("business_discovery")
	val businessDiscovery: BusinessDiscovery? = null
)

data class Cursors(

	@field:SerializedName("after")
	val after: String? = null
)

data class Paging(

	@field:SerializedName("cursors")
	val cursors: Cursors? = null
)

data class Media(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("paging")
	val paging: Paging? = null
)

data class DataItem(

	@field:SerializedName("like_count")
	val likeCount: Int? = null,

	@field:SerializedName("comments_count")
	val commentsCount: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("media_url")
	val mediaUrl: String? = null
)

data class BusinessDiscovery(

	@field:SerializedName("media_count")
	val mediaCount: Int? = null,

	@field:SerializedName("followers_count")
	val followersCount: Int? = null,

	@field:SerializedName("media")
	val media: Media? = null,

	@field:SerializedName("id")
	val id: String? = null
)
