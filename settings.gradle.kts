rootProject.name = "rekt"

enableFeaturePreview("VERSION_CATALOGS")
dependencyResolutionManagement {
    versionCatalogs {
        create("rekt") {
            from(files("./rekt.libs.toml"))
        }
    }
}

include(
    ":rekt-core"
)
