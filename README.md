# Vehicle Identification — GitHub-ready Android MVP

This project is prepared for building an Android APK with GitHub Actions.

## Included
- Vehicle Identification branding
- Camera capture
- Gallery/photo selection
- Online/offline indicator
- Vehicle database framework
- Saved Vehicles framework
- Debug APK build workflow

## Build from an Android phone
1. Upload the contents of this project to a GitHub repository.
2. Make sure the repository root contains `app/`, `build.gradle`, and `settings.gradle`.
3. The workflow is already in `.github/workflows/build-apk.yml`.
4. In GitHub, open **Actions** → **Build Vehicle Identification APK**.
5. Tap **Run workflow**.
6. When the run finishes successfully, open the run and download the **Vehicle-Identification-APK** artifact.
7. Extract it and install `app-debug.apk` on the Android phone.

## Important
This is the GitHub-ready MVP build infrastructure. It does not yet contain a production vehicle-recognition model or a worldwide vehicle dataset. Those are separate components to be added after the APK build is working.
