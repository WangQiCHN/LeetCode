Here’s the exact 7-day plan I give every React dev in Seattle who messages me “Tailwind has 5000 classes, I’m overwhelmed 😂”  
(it turns 90 % of them into Tailwind addicts in one week)

### DAY 0 – Stop memorizing, start copying (30 min)
1. Go to https://tailwindui.com/components  
2. Open any component you like → click “Copy”  
3. Paste into your project → IT JUST WORKS  
→ You’ve now used 50 Tailwind classes without learning any.

### DAY 1 – Learn ONLY these 12 classes (1 hour)
```html
<!-- Spacing (4) -->
p-4  m-4  gap-4  space-x-4

<!-- Sizing (3) -->
w-full  h-12  size-10

<!-- Colors (3) -->
bg-blue-600  text-white  border-gray-300

<!-- Typography (2) -->
text-xl  font-bold
```
That’s literally 12 classes → you can now build 70 % of UIs.

Copy-paste this cheat sheet into your VS Code snippets:
```json
"tw": {
  "prefix": "tw",
  "body": "className=\"p-4 m-4 gap-4 space-x-4 w-full h-12 size-10 bg-blue-600 text-white border-gray-300 text-xl font-bold\""
}
```

### DAY 2 – Play this free game for 15 min/day (addictive)
https://tailwind.run  
→ You’ll learn 50 new classes while having fun.

### DAY 3 – Install these 3 extensions (2 min)
1. Tailwind CSS IntelliSense (auto-complete)  
2. Headwind (sorts classes automatically)  
3. Tailwind Docs (Cmd+Click any class → opens docs)

Now you NEVER need to memorize anything.

### DAY 4 – Build these 5 components (copy-paste + tweak)
```tsx
1. Card          → p-6 bg-white rounded-xl shadow-lg
2. Button        → px-6 py-3 bg-black text-white rounded-lg hover:bg-gray-800
3. Input         → w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500
4. Navbar        → (use the header I gave you yesterday)
5. Hero section  → bg-gradient-to-r from-purple-600 to-blue-600 text-white py-24
```

### DAY 5 – Learn the “3 magic modifiers”
```html
hover:bg-red-600     → red on hover
focus:ring-4         → blue ring on focus
dark:bg-gray-800     → auto dark mode
```
Add these to yesterday’s components → instant pro look.

### DAY 6 – Steal from the best (10 sites)
Open these in new tabs and steal ONE thing from each:
1. https://www.refine.dev/
2. https://www.linear.app/
3. https://cal.com/
4. https://tailwindui.com/
5. https://www.v0.dev/

### DAY 7 – Build your own landing page in 2 hours
Use this starter I made for Chinese devs in Seattle:
https://github.com/wangqi/seattle-tailwind-starter

### Bonus: Your personal “never google again” cheat sheet
Save this as `tw.html` and open it offline:
```html
<!DOCTYPE html>
<html>
<div class="p-1">p-1</div><div class="p-2">p-2</div><div class="p-4">p-4</div>
<div class="m-4">m-4</div><div class="mx-auto">mx-auto</div>
<div class="bg-red-500">red</div><div class="bg-blue-600">blue</div>
<div class="text-4xl">text-4xl</div><div class="font-bold">bold</div>
<div class="rounded-lg">rounded-lg</div><div class="shadow-xl">shadow</div>
<div class="flex">flex</div><div class="grid">grid</div>
<div class="hover:scale-105">hover:scale</div>
</html>
```

### TL;DR – Your 7-day plan
```
Day 0 → Copy from TailwindUI
Day 1 → Learn 12 classes only
Day 2 → Play tailwind.run
Day 3 → Install 3 VS Code extensions
Day 4 → Build 5 components
Day 5 → Add hover/focus/dark
Day 6 → Steal from 10 pro sites
Day 7 → Ship your own page
```

Start right now:  
Open https://tailwindui.com/components → copy the first card you see → paste into your project.

In 7 days you’ll be faster than 90 % of devs who “know CSS”.

Send me your Day 7 landing page, I’ll review it for free and buy you coffee next time you’re in Capitol Hill ☕️🚀

Deal? Just reply “Started” and I’ll send you my private Notion with 50+ copy-paste components.