package com.github.roroche.plantuml.classes

import ch.ifocusit.plantuml.classdiagram.model.clazz.Clazz

/**
 * Utility class to filter [Classes].
 *
 * @param origin The original [Classes] to filter.
 * @property ignored The [Classes] to be ignored.
 */
class ClsFiltered(
    origin: Classes,
    private val ignored: List<String>
) : Classes.Wrap(origin) {
    /**
     * @return Filtered classes to be used for diagram generation.
     */
    override fun list(): List<Class<out Any>> {
        return when {
            origin.list().isEmpty() -> {
                emptyList()
            }
            ignored.isEmpty() -> {
                origin.list()
            }
            else -> {
                origin.list().filterNot { clazz ->
                    ignored.any { it.toRegex().matches(clazz.name) }
                }
            }
        }
    }
}
