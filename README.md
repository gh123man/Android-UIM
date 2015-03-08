#Android User Interface Markup (UIM)

UIM enables developers to build android apps with dynamic user interfaces. UIs are generated with JSON objects passed as strings.

##Features
 - Add more View property support
 - Add more native views (and their events)
 - Support for many android UI APIs. (More coming soon)
 
##Usage
    More documentation coming soon
 
##Handling Events
    More documentation coming soon

##TODO
 - Build UIs in real time
 - capture events in a global event handler

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

