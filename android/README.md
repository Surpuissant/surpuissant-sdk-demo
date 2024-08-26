# Demo Android - SDK Surpuissant

## Description
Cette démo illustre comment intégrer et utiliser notre SDK de reconnaissance de mots-clés dans une application Android. Le SDK permet de détecter des mots-clés prédéfinis même lorsque le téléphone est en veille, d'exécuter des commandes vocales, et de déclencher des actions via des webhooks tout en respectant la vie privée des utilisateurs.

## Fonctionnalités
- Détection de mots-clés en arrière-plan.
- Réactions personnalisées à des phrases d'action.
- Déclenchement d'actions via des webhooks après la détection.

## Documentation

Pour accéder à la documentation : [Documentation](https://docs.surpuissant.io/)

## Exemple de code

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // ...

        Surpuissant.setup(
            Configuration(
                context = applicationContext,
                apiKey = "your_api_key",
                keywords = listOf("surpuissant")
            )
        )

        Surpuissant.askRecordPermission(this) { _ ->
            Surpuissant.startRecord { status: Status ->
                Log.d("MainActivity", "startRecord: $status")
            }
        }

        Surpuissant.onKeyWordDetected = { inferenceResult ->
            Log.d("MainActivity", "onKeyWordDetected: $inferenceResult")
        }

        // ...
    }
}
```