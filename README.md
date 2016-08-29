# autochime

**Autochime** is an Android app created at Chimehack 3 @Facebook, Menlo Park.


## Inspiration 
This project has been built at Chimehack 3 at Facebook HQ and is inspired by alarming statistics showing low justice rates related to violence against women. What resonated with us is the low investigation rate. Most of the cases are not looked at because of the lack of evidence which we thought is the first step to achieving justice. We want to minimize actions required by victims as much as possible which lead us to a smartphone application that can automate violence detection, gather evidence, and request for help.

## What it does 
Autochime is a convenient way to automatically record evidence for sexual or physical assaults. In its initial state, it will look for certain predefined, and later trained, patterns which are associated with incidents of abovementioned type. Once its matches shaky movements and high volume sounds or specific user-customized keywords, it will trigger an alarm which simultaneously starts a recording procedure. This will be sent together with a GPS location to trusted parties, such as friends or family, via SMS. The message also includes a direction to a website where a log of incidents is journaled, upon which they decide to notify authorities or not. The recordings can be later used to take legal action, identify the offender, and bring him/her to justice. This disrupting approach is supposed to encourage women to report as they have credible evidence to rely upon. Furthermore, it may stop violation on spot or even avoid the assault to be happened, as the awareness of the app among potential offenders may raise the anxiety to be caught.

## How we built it
We begun by putting all of our ideas on the table. Some teammates suggested connecting friends with victims, some suggested integrating with Facebook, and some suggested using the phone’s sensors. We pooled our ideas together and AutoChime was born. Only two of us have Android experience so it was a great learning experience for all of us. We kept the workflow as parallel as possible by writing down all the major tickets on the whiteboard and letting individuals pick which tickets they are most interested in. In the end, all major features were implemented (Albeit, there will always be hacks to mask the bugs as this is a Hackathon).

##Challenges we ran into
Leveraging Google Maps API with zero Android experience. Misunderstanding Intent and Activities derailed me for quite some time.

##Accomplishments
that we’re proud of Taken into account, that the half of the team did not have Android or any mobile development experience, and some did not even used Java before, we did an incredible job of supporting each other in the development of code. Teamwork was essential to make autochime become reality.

##What's next for AutoChime
There a several features that will be added upon the current version. The major features include: manual triggered process flows notifying authorities directly; smart detection to check if other people are in surrounding; build sensor instead of using a phone as platform and make it easily portable to various objects (watches, etc.); leverage various API to make UI more intuitive, f.e. elaborate on Google Maps; natural language processing for voice recognition + activation if user voice only; machine learning to improve violence detection and decrease false negatives; decoy alarm set off screen (i.e. faking a phone call).

## Team Members

1. [Wilbur Yu](https://github.com/yuwilbur)
2. [Muhammed Guelen](https://github.com/mguelen)
3. [Tiana Im](https://github.com/imtiana)
4. [Amy Tsai](https://github.com/ayatsai)
