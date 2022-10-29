package dev.rjackson.metrolinkstops.data

data class Stop(
    val name: String,
    val favourite: Boolean = false
) {
    fun toPreferenceString(): String = name

    companion object {
        fun String.toStop(): Stop = Stop(name = this)
    }
}
