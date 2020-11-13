import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        NavigationView {
            listView()
                .navigationBarTitle("ESB Lights")
                .navigationBarItems(trailing:
                                        Button("Reload") {
                                            self.viewModel.loadLights()
                                        })
        }
    }
    
    private func listView() -> AnyView {
        switch viewModel.lights {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let lodableLights):
            return AnyView(LightsView(lights: lodableLights))
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

extension ContentView {
    enum LoadableLights {
        case loading
        case result(Lights)
        case error(String)
    }
    
    class ViewModel: ObservableObject {
        let repo : LightRepository
        @Published var lights = LoadableLights.loading
        
        init(repo: LightRepository){
            self.repo = repo
            self.loadLights()
        }
        
        func loadLights() {
            self.lights = .loading
            repo.getLights(completionHandler: { lights, error in
                if let lights = lights {
                    self.lights = .result(lights)
                } else {
                    self.lights = .error(error?.localizedDescription ?? "error")
                }
            })
        }
    }
}
