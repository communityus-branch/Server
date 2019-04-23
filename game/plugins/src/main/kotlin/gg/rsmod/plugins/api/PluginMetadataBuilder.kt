package gg.rsmod.plugins.api

import gg.rsmod.game.plugin.PluginMetadata

/**
 * @author Tom <rspsmods@gmail.com>
 */
class PluginMetadataBuilder {

    private lateinit var name: String

    private var propertyFileName: String? = null

    private val authors = mutableSetOf<String>()

    private var description: String = ""

    private val properties = mutableMapOf<String, Any>()

    fun build(): PluginMetadata {
        check(::name.isInitialized) { "Plugin name not set." }
        return PluginMetadata(propertyFileName, name, description, authors, properties.toMap())
    }

    fun setPropertyFileName(name: String): PluginMetadataBuilder {
        propertyFileName = name
        return this
    }

    fun setName(name: String): PluginMetadataBuilder {
        this.name = name
        return this
    }

    fun setDescription(description: String): PluginMetadataBuilder {
        check(this.description.isEmpty()) { "Description already set." }
        this.description = description
        return this
    }

    fun setAuthors(authors: Set<String>): PluginMetadataBuilder {
        check(authors.isEmpty()) { "Author already set. Use `addAuthor` instead." }
        this.authors.addAll(authors)
        return this
    }

    fun addAuthor(author: String): PluginMetadataBuilder {
        authors.add(author)
        return this
    }

    fun addProperty(tag: String, value: Any): PluginMetadataBuilder {
        properties[tag] = value
        return this
    }
}