import SwiftUI
import shared
import URLImage

struct LightsView: View {
    var lights: Lights
    
    var body: some View {
        Color.black.overlay(
            ScrollView {
            VStack(){
                TodaysView(picture: lights.picture, todayColor: lights.todayColor)
                Spacer().frame(height: 50)
                CalendarView(items: lights.calendar)
            }
            }
        )
    }
}

struct TodaysView: View {
    var picture: String
    var todayColor: String
    
    var body: some View {
        Text("Tonight's lights")
            .font(.system(size: 25, design: .monospaced))
            .foregroundColor(Color.white)
            .tracking(2)
        ZStack(alignment: .bottom) {
            URLImage(url: URL(string: picture)!,
                     content: { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                     })
            Text(todayColor.uppercased())
                .tracking(2)
                .padding(.bottom, 10)
                .padding(.top, 10)
                .padding(.horizontal, 30)
                .foregroundColor(Color.white)
                .overlay(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(Color.white, lineWidth: 1)
                ).background(RoundedRectangle(cornerRadius: 8)
                                .fill(Color.black))
        }
        
    }
}

struct CalendarView : View {
    var items: [DayLight]
    
    var body: some View {
        Text("Next lights")
            .font(.system(size: 25, design: .monospaced))
            .foregroundColor(Color.white)
            .tracking(2)
        ScrollView(.horizontal) {
            HStack {
                ForEach(items, id: \.self) { item in
                    CalendarItem(item: item)
                }
            }
        }
    }
}

struct CalendarItem : View {
    var item: DayLight
    
    var body: some View {
        VStack() {
            URLImage(url: URL(string: item.image)!,
                     content: { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                     })
            Text(item.color.uppercased())
                .foregroundColor(Color.white)
                .padding(4)
            
            Text(item.reason)
                .foregroundColor(Color.white)
                .padding(.horizontal, 4)
                .alignmentGuide(.leading) { d in d[.leading] }
            Spacer()
            Text(item.date)
                .foregroundColor(Color.white)
                .padding(2)
                .alignmentGuide(.leading) { d in d[.trailing] }
                .font(.system(size: 8))
        }.frame(minWidth: 150, idealWidth: 150, maxWidth: 150, minHeight: 200, idealHeight: 300, maxHeight: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .top)
    }
}

struct LightsView_Previews: PreviewProvider {
    
    static var previews: some View {
        let lightsSample = Lights(todayColor : "So Blue",
                                  picture : "https://www.esbnyc.com/sites/default/files/2020-01/BlueBlueBlue.jpg",
                                  calendar : [
                                    DayLight(
                                        image : "https://www.esbnyc.com//sites/default/files/styles/4_cols/public/2020-09/Estee%20Lauder%202020%20Lighting.jpg?itok=m-p2yhSl",
                                        color : "PINK",
                                        reason : "With Scrolling Pink Ribbons in Honor of The Estée Lauder Companies’ Breast Cancer Campaign",
                                        date : "2020-10-01"
                                    ),
                                    DayLight(
                                        image : "https://www.esbnyc.com//sites/default/files/styles/4_cols/public/2020-02/Blue-edited.jpg?itok=IY4XWCt-",
                                        color : "Blue",
                                        reason : "In Honor of STOMP Out Bullying and World Day of Bullying Prevention 2020",
                                        date : "2020-10-05"
                                    ),
                                    DayLight(
                                        image : "https://www.esbnyc.com//sites/default/files/styles/4_cols/public/2020-01/RWB.jpg?itok=7YpG3a0d",
                                        color : "RED, WHITE & BLUE",
                                        reason : "In Celebration of the New York Rangers’ Overall Number 1 Draft Pick",
                                        date : "2020-10-06"
                                    )
                                  ])
        
        LightsView(lights: lightsSample)
    }
}
