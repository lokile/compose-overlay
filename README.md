# ComposeOverlay

`ComposeOverlay` is a lightweight Android library that lets you display Compose UI as overlays on top of any running activity — without requiring any changes to the activity's layout.

It’s ideal for in-app toasts, floating messages, or custom dialogs.

![demo](https://github.com/user-attachments/assets/87f9ad07-e869-40fb-83de-4d0cfdcb0792)

---

## ✨ Features

- Works with any `ComponentActivity`.
- Automatically removes itself when no longer displayed in the UI.
- Can be called from anywhere in your codebase and from any thread

---

## 🚀 Getting Started
>You can check out the demo app to see how it works in practice.

### 1. Implement `OverlayContent`

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

### 2. Show your overlay

```kotlin
ComposeOverlay.show(HelloDialog(...))
```

### How it works
- `ComposeOverlay.show()` publishes an internal event containing your `OverlayRenderer`
- When the activity is resumed,  it adds a `ComposeView` to the activity’s root `ViewGroup` and calls `render()`
- If `render()` returns false, a `DisposableEffect` removes the `ComposeView` automatically.


### License
This project is released under the MIT License.

### Maintainer
Made by Loki Le. PRs, issues, and suggestions are welcome!
