#Android User Interface Markup (UIM)

UIM enables developers to build android apps with dynamic user interfaces. UIs are generated with JSON objects.


##Features
 - Build UIs in real time
 - Capture events in a global event handler
 - Support for many android UI APIs. (More coming soon)
 - Package views together in an event (more on this in Handling Events)

##TODO
 - Add more View property support
 - Add more native views (and their events)

##Usage
 1. Implement `IuimEvents`
 2. Construct a `UiAssembler`
 3. call `UiAssembler.parseAndApplyView()` when you are ready to build the UI and set up event handlers. `.parseAndApplyView()` will automatically add the new view to the parent you supplied in the constructor. 
 
##Handling Events
    More documentation coming soon

##Example

    {
        "Version": 1,
        "LinearLayout": {
            "layout_width": "match_parent",
            "layout_height": "match_parent",
            "uim_children": [ 
                { 
                    "Button": {
                        "id": "test1",
                        "text": "test1",
                        "gravity": "left|center",
                        "layout_width": "wrap_content",
                        "layout_height": "wrap_content",
                        "uim_package": [
                            "test2"
                        ]
                    }
                },
                {
                    "SeekBar": {
                        "id": "test2",
                        "text": "test2",
                        "layout_width": "match_parent",
                        "layout_height": "wrap_content"
                    }
                }
            ]
        }
    }

####Produces

![Example](screenshots/example1.png)

