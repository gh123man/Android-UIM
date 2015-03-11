#Android User Interface Markup (UIM)

UIM enables developers to build android apps with dynamically generated native user interfaces at runtime. UIs are generated with JSON objects following a format much like Android native XML.


##Features
 - Build UIs at runtime
 - Capture events in a global event handler
 - Support for many android UI APIs. (More coming soon)
 - Package views together in an event - more on this in [Handling Events](#handling-events)

##TODO
 - Add more View property support
 - Add more native views (and their events)
 
##API
Full Javadocs of the public API can be found [here](http://gh123man.github.io/Android-UIM/)
 
###Summary of the public API
 
Class               |     Description
---------           |     -----------
[Event](http://gh123man.github.io/Android-UIM/com/brian/floersch/uim/AssemblerV1/events/Event.html)      | Contains the context of an event
[IuimEvents](http://gh123man.github.io/Android-UIM/com/brian/floersch/uim/IuimEvents.html)  | Events used by UIM (you must implement this)
[UiAssembler](http://gh123man.github.io/Android-UIM/com/brian/floersch/uim/UiAssembler.html)  | This is the core entry point of the library. This class facilitates the view assembly process.
 
##Usage
 1. Implement `IuimEvents`
 2. Construct a `UiAssembler`
 3. call `UiAssembler.parseAndApplyView()` when you are ready to build the UI and set up event handlers. `.parseAndApplyView()` will automatically add the new view to the parent you supplied in the constructor. 
 
##Supported Views and layouts
###Supports layout/view up to API level 10 (2.3.3)
All listed views and layouts are supported to some degree. 
 - [LinearLayout](http://developer.android.com/reference/android/widget/LinearLayout.html) - complete
 - [LinerLayout.LayoutParams](http://developer.android.com/reference/android/widget/LinearLayout.LayoutParams.html) - complete
 - [RelativeLayout](http://developer.android.com/reference/android/widget/RelativeLayout.html) - complete
 - [RelativeLayout.LayoutParams](http://developer.android.com/reference/android/widget/RelativeLayout.LayoutParams.html) - complete
 - [ViewGroup.MarginLayoutParams](http://developer.android.com/reference/android/view/ViewGroup.MarginLayoutParams.html) - complete
 - [ViewGroup.LayoutParams](http://developer.android.com/reference/android/view/ViewGroup.LayoutParams.html) - complete
 - [ViewGroup](http://developer.android.com/reference/android/view/ViewGroup.LayoutParams.html) - complete
 - [View](http://developer.android.com/reference/android/view/View.html) - very incomplete
 - [View.OnClickListener.onClick](http://developer.android.com/reference/android/view/View.OnClickListener.html) handled
 - [View.OnLongClickListener.onLongClick](http://developer.android.com/reference/android/view/View.OnLongClickListener.html) handled
 - [TextView](http://developer.android.com/reference/android/widget/TextView.html) - very incomplete
 - [Button](http://developer.android.com/reference/android/widget/Button.html) - complete, but missing inherited text view components
 - [SeekBar](http://developer.android.com/reference/android/widget/SeekBar.html) - incomplete
 - [SeekBar.OnSeekBarChangeListener.onStopTrackingTouch](http://developer.android.com/reference/android/widget/SeekBar.OnSeekBarChangeListener.html#onStopTrackingTouch(android.widget.SeekBar)) - handled

##Special JSON Attributes
Attribute           |     Description
---------           |     -----------
`uim_children`      | An array of children, can be any view or layout
`uim_eventPackage`  | An array of string IDs of other elements. These elements will be packaged in the event propagated by the view this is attached to. NOTE: you can reference an ID here before it is defined in a view (as long as it is defined at some point later)
`uim_handleEvents`  | An array of strings of supported events. Only events supported by the view will be handled.  

##Handling Events
When you implement `IuimEvents`, you must override `onEvent(Event event)`.
This method is called whenever a view receives a supported event. The event class contains the String ID of the object provided in JSON, the View itself and an array of packaged views (as defined in JSON). It is up to you to decide how you want to handle these events. 

##Simple Example

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
                        "uim_handleEvents": [
                            "onClick"
                        ],
                        "uim_eventPackage": [
                            "test2"
                        ]
                    }
                },
                {
                    "SeekBar": {
                        "id": "test2",
                        "layout_width": "match_parent",
                        "layout_height": "wrap_content"
                    }
                }
            ]
        }
    }

####Produces

![Example](screenshots/example1.png)

##A remote control example
    {
        "Version": 1,
        "RelativeLayout": {
            "layout_width": "match_parent",
            "layout_height": "match_parent",
            "uim_children": [
                {
                    "LinearLayout": {
                        "id": "controls",
                        "layout_width": "match_parent",
                        "layout_height": "120dp",
                        "orientation": "horizontal",
                        "uim_children": [
                            { 
                                "Button": {
                                    "id": "back",
                                    "text": "<<",
                                    "layout_width": "wrap_content",
                                    "layout_height": "match_parent",
                                    "weight": 1,
                                    "uim_handleEvents": [
                                        "onClick"
                                    ]
                                }
                            },
                            { 
                                "Button": {
                                    "id": "playPause",
                                    "text": "Play/Pause",
                                    "layout_width": "wrap_content",
                                    "layout_height": "match_parent",
                                    "weight": 3,
                                    "uim_handleEvents": [
                                        "onClick"
                                    ]
                                }
                            },
                            { 
                                "Button": {
                                    "id": "forward",
                                    "text": ">>",
                                    "layout_width": "wrap_content",
                                    "layout_height": "match_parent",
                                    "layout_alignTop": "playPause",
                                    "weight": 1,
                                    "uim_handleEvents": [
                                        "onClick"
                                    ]
                                }
                            }
                        ]
                    }
                },
                { 
                    "TextView": {
                        "id": "volume",
                        "text": "Volume",
                        "layout_width": "wrap_content",
                        "layout_height": "wrap_content",
                        "layout_below": "controls",
                        "layout_centerHorizontal": true,
                        "layout_marginTop": "20dp"
                    }
                },
                {
                    "SeekBar": {
                        "id": "seek",
                        "layout_width": "match_parent",
                        "layout_height": "wrap_content",
                        "layout_below": "volume",
                        "layout_centerHorizontal": true,
                        "uim_handleEvents": [
                            "onStopTrackingTouch"
                        ]
                    }
                }
            ]
        }
    }
                
####Produces

![Example](screenshots/example2.png)