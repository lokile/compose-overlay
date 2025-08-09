# ComposeOverlay

`ComposeOverlay` is a lightweight Android library that lets you display Compose UI as overlays on top of any running activity — without requiring any changes to the activity's layout.

It’s ideal for floating messages or custom dialogs.

![demo](https://github.com/user-attachments/assets/87f9ad07-e869-40fb-83de-4d0cfdcb0792)

---

## ✨ Features

- Works with any `ComponentActivity`.
- Automatically cleans itself up when it’s no longer visible in the UI.
- Callable from anywhere in your codebase, from any thread

---

## 🚀 Getting Started
>You can check out the demo app to see how it works.

### 1. Installation
Add the dependency to your app build.gradle file, the latest version is: [![Maven Central](https://img.shields.io/maven-central/v/io.github.lokile/compose-overlay?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.lokile/compose-overlay)

```
  dependencies {
    implementation("io.github.lokile:compose-overlay:latest_version")
  }
```




### 2. Implement `OverlayContent`

```kotlin
class HelloDialog(
    private val message: String,
    private val onCancel: () -> Unit
) : OverlayContent {

    /**
     * Renders the UI.
     *
     * @return true to keep the UI visible; or `false` to remove it from the activity.
     */
    @Composable
    override fun render(): Boolean {
        var isVisible by remember { mutableStateOf(true) }

        Dialog(onDismissRequest = {
            isVisible = false
            onCancel()
        }) {
            Text(text = message)
        }

        return isVisible
    }
}
```

### 3. Show your overlay

```kotlin
ComposeOverlay.show(HelloDialog(...))
```

---
## License
This project is released under the MIT License.

### Maintainer
Made by Loki Le. PRs, issues, and suggestions are welcome!
