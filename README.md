Founding_Test
This repository contains a high-performance Home Goods Discovery Application built as a technical submission for the Founding Engineer position. The project demonstrates a focus on modern Android development practices, clean MVVM architecture, and high-fidelity UI design.
🛠 Tech Stack
Language: Kotlin
UI: Jetpack Compose (Native Android)
Architecture: MVVM (Model-View-ViewModel)
Image Loading: Coil (with memory/disk caching for optimized performance)
Data Parsing: kotlinx.serialization (configured for resilient schema handling)
💡 Key Engineering Decisions
Resilient Data Parsing: The source JSON contained inconsistent types for price (String vs. Number). I implemented a custom decoder to ensure the application remains stable and provides fallback values rather than crashing on malformed data.
Component-Based UI: The project utilizes a modularized component library for ProductCard and PhilosophyBanner, ensuring the codebase remains maintainable and scalable.
State-Driven Navigation: I implemented a reactive navigation layer that manages screen transitions based on UI state, avoiding heavy external dependencies while keeping the user experience fluid.
Editorial Layouts: The Home screen utilizes a custom grid spanning logic to mix product listings with full-width editorial banners, creating an immersive, retail-focused user experience.
🚀 Performance Optimizations
Background Caching: Integrated Coil for image handling, utilizing a dual-caching strategy (Memory + Disk) to ensure smooth scrolling through large catalogs.
Defensive UI: The "Add to Cart" button is dynamically bound to the inStock data point, providing clear, instant visual feedback for users.
⚖️ Trade-offs & Future Scaling
The Trade-off
To prioritize an offline-first experience, I implemented client-side filtering. While efficient for the current dataset, I recognize that scaling to 100,000+ items would require moving to a Paging 3 implementation with server-side search to maintain optimal memory management.
Next Steps
Offline Persistence: Implement local database storage via Room.
Advanced Discovery: Add multi-select filter parameters for more granular user control.
Stability: Integrate automated UI/Unit tests to maintain stability during rapid development.
Built by Ahmed Fayaz — Senior Mobile Application Developer
